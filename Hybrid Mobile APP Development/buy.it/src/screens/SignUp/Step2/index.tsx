import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { CompositeScreenProps } from "@react-navigation/native";
import { ArrowRight } from "phosphor-react-native";
import { useForm, SubmitHandler, Controller } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { useLayoutEffect, useState } from "react";
import { api } from '@services/api';

// Component import
import {
  DecreasingContainer,
  Input,
  Button,
  Chip,
  DefaultComponent,
  CustomDropdown,
  WrapperPage,
  Loading
} from "@components/index";

// Type import
import { MainNavigationRoutes } from "@routes/index";
import { SignUpRoutes } from "..";

// Validation import
import { Step2FormSchema } from "@validations/index";

// Theme import
import theme from "@theme/index";

// Util import
import { toMaskedCNPJ, unMask } from "@utils/masks";

// Hook import
import { useSignUpForm } from "@hooks/useSignUpForm";

// Style import
import { Fieldset, WrapChip } from './styles';
import { ScrollableContent } from "@global/styles/index";

// Type import
import { Department } from "@dtos/department";
import { Tag } from "@dtos/tag";

interface Step2Form {
  nome: string;
  email: string;
  cnpj: string;
}

export const Step2: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<SignUpRoutes, 'Step2'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // Hook
  const { user, setUser } = useSignUpForm();
  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm<Step2Form>({
    resolver: yupResolver(Step2FormSchema),
  });

  const onSubmit: SubmitHandler<Step2Form> = (data) => {
    const cleanCNPJ = unMask(data.cnpj);
    Object.assign(data, { cnpj: cleanCNPJ });
    // Object.assign(data, { idsTags: selectedTags });

    setUser({ ...user, ...data });

    return navigation.navigate("Step3");
  }

  // State
  // const [departments, setDepartments] = useState<Department[]>([] as Department[]);
  // const [department, setDepartment] = useState<Department | null>(null);
  const [tags, setTags] = useState<Tag[]>([] as Tag[]);
  const [selectedTags, setSelectedTags] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useLayoutEffect(() => {
    const fetchData = async () => {
      try {

        const tagsResponse = await api.get('/tags');
        setTags(tagsResponse.data.content);
        
      } catch (error) {
        console.error('Erro ao buscar dados: ', error);
      } finally {
        setIsLoading(false)
      }
    };

    fetchData();
  }, []);

  if (isLoading) return <Loading />;

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            title: "Dados da",
            subtitle: "Passo 2 de 5",
            highlightedText: "empresa"
          }}
          key="default-component-step1"
        />

        <DecreasingContainer>
          <Fieldset>
            <Controller
              control={control}
              name="nome"
              render={({ field: { value, onChange } }) => (
                <Input
                  value={value}
                  onChangeText={onChange}
                  label="Nome da empresa"
                  placeholder="Carrefour"
                  error={errors.nome?.message}
                />
              )}
            />
          </Fieldset>

          <Fieldset>
            <Controller
              control={control}
              name="email"
              render={({ field: { value, onChange } }) => (
                <Input
                  value={value}
                  onChangeText={onChange}
                  label="Email principal"
                  placeholder="johndoe@example.com"
                  autoCapitalize='none'
                  error={errors.email?.message}
                />
              )}
            />
          </Fieldset>

          <Fieldset>
            <Controller
              control={control}
              name="cnpj"
              render={({ field: { value, onChange } }) => (
                <Input
                  value={value}
                  onChangeText={(text) => onChange(toMaskedCNPJ(text))}
                  label="CNPJ"
                  placeholder="00.000.000/0001-00"
                  keyboardType="numeric"
                  error={errors.cnpj?.message}
                />
              )}
            />
          </Fieldset>

          {/* Por enquanto não precisaremos desse campo, mas em breve ele será utilizado para buscarmos apenas
              as tags relacionadas ao departamento escolhido */}
          {/* { departments && (
            <Fieldset>
            <CustomDropdown
              label="Departamento"
              placeholder="Selecione uma opção"
              options={departments.map(dep => ({ label: dep.nome, value: dep.id}))}
              selectedValue={department ? department.id : undefined}
              onValueChange={(value: number) =>
                setDepartment(departments.find((dep) => dep.id === String(value)) || null)
              }
            />
          </Fieldset>
          ) } */}

          { tags && (
            <Fieldset>
            <CustomDropdown
              label="Tags relacionadas"
              isSearchable
              isMultiple
              placeholder="Selecione as tags relacionadas"
              options={tags.map(tag => ({ label: tag.nome, value: tag.id}))}
              selectedValue={selectedTags}
              onValueChange={(value: []) => setSelectedTags(value)}
            />
          </Fieldset>
          ) }

        </DecreasingContainer>
      </ScrollableContent>

      <Button
        label="Continuar"
        size="XL"
        icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={handleSubmit(onSubmit)}
      />
    </WrapperPage>
  );
}
