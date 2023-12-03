import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { CompositeScreenProps } from "@react-navigation/native";
import { ArrowRight, Check, X } from "phosphor-react-native";
import { useForm, SubmitHandler, Controller } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";

// Type import
import { MainNavigationRoutes } from "@routes/index";
import { SignUpRoutes } from "..";

// Validation import
import { Step5FormSchema } from "@validations/index";

// Theme import
import theme from "@theme/index";

// Hook import
import { useSignUpForm } from "@hooks/useSignUpForm";

// Component import
import {
  DecreasingContainer,
  Input,
  Button,
  DefaultComponent,
} from "@components/index";

// Style import
import { Container, Fieldset, Requirement, RequirementText } from './styles';

interface Step5Form {
  senha: string;
}

export const Step5: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<SignUpRoutes, 'Step5'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // Hook
  const { user, setUser } = useSignUpForm();
  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm<Step5Form>({
    resolver: yupResolver(Step5FormSchema),
  });

  const onSubmit: SubmitHandler<Step5Form> = (data) => {
    setUser({ ...user, ...data });

    return navigation.navigate("Profile");
  }

  return (
    <Container>
      <DefaultComponent
        headerProps={{ goBack: () => navigation.goBack() }}
        highlightProps={{
          subtitle: "Passo 5 de 5",
          title: "Defina uma",
          highlightedText: "senha"
        }}
        key="default-component-step5"
      />

      <DecreasingContainer>
        <Fieldset>
          <Controller
            control={control}
            name="senha"
            render={({ field: { value, onChange } }) => (
              <Input
                value={value}
                onChangeText={onChange}
                label="Senha"
                placeholder="********"
                secureTextEntry
                error={errors.senha?.message}
              />
            )}
          />
        </Fieldset>

        <Requirement>
          <Check color={theme.COLORS.GREEN_700} weight="bold" size={theme.FONT_SIZE.MD} />
          <RequirementText fulfilled>8 caracteres</RequirementText>
        </Requirement>

        <Requirement>
          <Check color={theme.COLORS.GREEN_700} weight="bold" size={theme.FONT_SIZE.MD} />
          <RequirementText fulfilled>pelo menos uma letra minúscula</RequirementText>
        </Requirement>

        <Requirement>
          <X color={theme.COLORS.RED} weight="bold" size={theme.FONT_SIZE.MD} />
          <RequirementText>pelo menos uma letra minúscula</RequirementText>
        </Requirement>

      </DecreasingContainer>

      <Button
        label="Criar conta"
        size="XL"
        icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={handleSubmit(onSubmit)}
      />
    </Container>
  );
}
