import { useState } from "react";
import { ImageSourcePropType } from "react-native";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { Checks } from "phosphor-react-native";
import { useForm, SubmitHandler, Controller } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import Toast from 'react-native-toast-message';

// Type import
import { MainNavigationRoutes } from "@routes/index";

// Validation import
import { SignInFormSchema as ProfileFormSchema } from "@validations/index";

// Theme import
import theme from "@theme/index";

// Component import
import {
  DecreasingContainer,
  Input,
  Button,
  DefaultComponent,
  UserAvatar
} from "@components/index";

// Style import
import { Container, Fieldset } from './styles';

interface ProfileForm {
  email: string;
  senha: string;
}

export function Profile({
  navigation
}: NativeStackScreenProps<MainNavigationRoutes, 'Profile'>) {
  // State
  const [avatar, setAvatar] = useState<string | null>(null);

  // Hook
  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm<ProfileForm>({
    resolver: yupResolver(ProfileFormSchema),
  });

  const onSubmit: SubmitHandler<ProfileForm> = (data) => {
    try {
      if (!avatar)
        return Toast.show({
          type: 'error',
          text1: 'Adicione uma imagem',
          text2: 'Sua identidade visual é importante.'
        });

    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Credenciais inválida.'
      });
    }
  }

  const imageSource: ImageSourcePropType =
    avatar ? { uri: avatar } : require('../../assets/default_avatar.png');

  const user = "John Doe";

  return (
    <Container>
      <DefaultComponent
        highlightProps={{
          title: "Gerencie sua conta",
          subtitle: `Olá, ${user}!`
        }}
        headerProps={{ goBack: () => navigation.goBack() }}
        key="default-component-profile"
      />

      <DecreasingContainer>
        <UserAvatar
          imageSource={imageSource}
          handleSetAvatar={setAvatar}
          size="MD"
        />

        <Fieldset>
          <Controller
            control={control}
            name="email"
            render={({ field: { value, onChange } }) => (
              <Input
                value={value}
                onChangeText={onChange}
                label="E-mail"
                keyboardType="email-address"
                placeholder="Johndoe@example.com"
                error={errors.email?.message}
              />
            )}
          />
        </Fieldset>

        <Fieldset style={{ paddingBottom: 75 }}>
          <Controller
            control={control}
            name="senha"
            render={({ field: { value, onChange } }) => (
              <Input
                value={value}
                onChangeText={onChange}
                label="Senha"
                placeholder="****"
                secureTextEntry
                error={errors.senha?.message}
              />
            )}
          />
        </Fieldset>
      </DecreasingContainer>

      <Button
        label="Salvar"
        size="XL"
        icon={<Checks color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={handleSubmit(onSubmit)}
      />
    </Container>
  );
}
