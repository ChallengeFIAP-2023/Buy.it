import { useState } from "react";
import { ImageSourcePropType } from "react-native";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { Checks } from "phosphor-react-native";

// Type import
import { MainNavigationRoutes } from "@routes/index";

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

export function Profile({
  navigation
}: NativeStackScreenProps<MainNavigationRoutes, 'Profile'>) {
   // State
   const [avatar, setAvatar] = useState<string | null>(null);

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
          <Input label="E-mail" placeholder="Johndoe@example.com" />
        </Fieldset>

        <Fieldset>
          <Input
            label="Senha"
            placeholder="****"
            secureTextEntry
          />
        </Fieldset>
      </DecreasingContainer>

      <Button
        label="Salvar"
        size="XL"
        icon={<Checks color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={() => navigation.navigate("Profile")}
      />
    </Container>
  );
}
