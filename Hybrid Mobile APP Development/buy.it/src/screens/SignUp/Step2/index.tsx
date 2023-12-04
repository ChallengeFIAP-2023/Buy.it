import { useLayoutEffect, useState } from "react";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { CompositeScreenProps } from "@react-navigation/native";
import { ArrowRight } from "phosphor-react-native";
import { useForm, SubmitHandler, Controller } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { ActivityIndicator, RefreshControl } from "react-native";

// Service import
import { api } from '@services/api';

// Component import
import {
  DecreasingContainer,
  Input,
  Button,
  DefaultComponent,
  CustomDropdown,
  WrapperPage
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
import { WrapperLoading } from './styles';
import { ScrollableContent, Fieldset } from "@global/styles/index";

// Type import
import { Tag } from "@dtos/tag";
import Toast from "react-native-toast-message";

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

  // State
  const [tags, setTags] = useState<Tag[]>([]);
  const [selectedTags, setSelectedTags] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  const onSubmit: SubmitHandler<Step2Form> = (data) => {
    const cleanCNPJ = unMask(data.cnpj);
    Object.assign(data, { cnpj: cleanCNPJ });

    setUser({ ...user, ...data });

    return navigation.navigate("Step3");
  }

  const fetchData = async () => {
    try {
      const { data } = await api.get('/tags');
      setTags(data.content);
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível buscar as tags'
      });
    } finally {
      setIsLoading(false);
    }
  };

  useLayoutEffect(() => {
    fetchData();
  }, []);

  return (
    <WrapperPage>
      <ScrollableContent
        refreshControl={
          <RefreshControl refreshing={isLoading} onRefresh={fetchData} />
        }
      >
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            title: "Dados da",
            subtitle: "Passo 2 de 5",
            highlightedText: "empresa"
          }}
          key="default-component-step2"
        />

        <DecreasingContainer scrollable>
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
                  label="E-mail principal"
                  keyboardType="email-address"
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

          {!isLoading && tags && (
            <Fieldset>
              <CustomDropdown
                label="Tags relacionadas"
                isSearchable
                isMultiple
                placeholder="Selecione as tags relacionadas"
                options={tags.map(tag => ({ label: tag.nome, value: tag.id }))}
                selectedValue={selectedTags}
                onValueChange={(value: []) => setSelectedTags(value)}
              />
            </Fieldset>
          )}
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
