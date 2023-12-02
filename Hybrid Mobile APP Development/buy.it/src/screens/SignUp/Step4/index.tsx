import { useState } from "react";
import { ImageSourcePropType } from "react-native";
import { ArrowRight } from "phosphor-react-native";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { CompositeScreenProps } from "@react-navigation/native";

// Component import
import {
  DecreasingContainer,
  Button,
  DefaultComponent,
  UserAvatar
} from "@components/index";

// Type import
import { MainNavigationRoutes } from "@routes/index";
import { SignUpRoutes } from "..";

// Theme import
import theme from "@theme/index";

// Style import
import { Container, AvatarWrapper } from './styles';

export const Step4: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<SignUpRoutes, 'Step4'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // State
  const [avatar, setAvatar] = useState<string | null>(null);

  const imageSource: ImageSourcePropType =
    avatar ? { uri: avatar } : require('../../../assets/default_avatar.png');

  return (
    <Container>
      <DefaultComponent
        headerProps={{ goBack: () => navigation.goBack() }}
        highlightProps={{
          subtitle: "Passo 4 de 5",
          title: "Logo da empresa"
        }}
        key="default-component-step4"
      />

      <DecreasingContainer>
        <AvatarWrapper>
          <UserAvatar
            imageSource={imageSource}
            handleSetAvatar={setAvatar}
            size="XLL"
          />
        </AvatarWrapper>
      </DecreasingContainer>

      <Button
        label="Continuar"
        size="XL"
        icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={() => navigation.navigate("Step5")}
      />
    </Container>
  );
}
