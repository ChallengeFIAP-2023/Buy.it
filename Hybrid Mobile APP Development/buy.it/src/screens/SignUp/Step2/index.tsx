import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { CompositeScreenProps } from "@react-navigation/native";
import { ArrowRight } from "phosphor-react-native";
import { useForm, SubmitHandler, Controller } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";

// Component import
import {
  DecreasingContainer,
  Input,
  Button,
  Chip,
  DefaultComponent,
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
import { Fieldset, WrapChip } from './styles';
import { ScrollableContent } from "@global/styles/index";

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

    setUser({ ...user, ...data });

    return navigation.navigate("Step3");
  }

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

          <Fieldset>
            <Input label="Departamento" placeholder="Escritório" />
          </Fieldset>

          <Input label="Tags relacionadas" placeholder="Papelaria" />

          <WrapChip>
            <Chip value="material escolar" removable />
            <Chip value="suprimento" removable />
            <Chip value="papelaria" removable />
          </WrapChip>

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
