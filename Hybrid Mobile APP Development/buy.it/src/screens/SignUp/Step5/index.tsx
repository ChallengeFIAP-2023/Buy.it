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

// Type import
import { UserQuery } from "@dtos/user";

// Hook import
import { useSignUpForm } from "@hooks/useSignUpForm";
import { useAuth } from "@hooks/useAuth";

// Component import
import {
  DecreasingContainer,
  Input,
  Button,
  DefaultComponent,
  WrapperPage
} from "@components/index";

// Style import
import { Fieldset, Requirement, RequirementText, Content } from './styles';
import { ScrollableContent } from '@global/styles/index';

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
  const { user, handleRegisterUser } = useSignUpForm();
  const { handleSignIn } = useAuth();
  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm<Step5Form>({
    resolver: yupResolver(Step5FormSchema),
  });

  const onSubmit: SubmitHandler<Step5Form> = async (data) => {
    const email = user.email;
    const senha = data.senha;

    const finalUserData: UserQuery = Object.assign(user, { senha });

    try {
      await handleRegisterUser(finalUserData);

      if (!email || !senha) return;

      await handleSignIn({ email, password: senha })
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <WrapperPage>
      <ScrollableContent>
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
          <Content>
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
              <RequirementText fullFilled>8 caracteres</RequirementText>
            </Requirement>

            <Requirement>
              <Check color={theme.COLORS.GREEN_700} weight="bold" size={theme.FONT_SIZE.MD} />
              <RequirementText fullFilled>pelo menos uma letra minúscula</RequirementText>
            </Requirement>

            <Requirement>
              <X color={theme.COLORS.RED} weight="bold" size={theme.FONT_SIZE.MD} />
              <RequirementText>pelo menos uma letra minúscula</RequirementText>
            </Requirement>
          </Content>

        </DecreasingContainer>
      </ScrollableContent>

      <Button
        label="Criar conta"
        size="XL"
        icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={handleSubmit(onSubmit)}
      />
    </WrapperPage>
  );
}
