import { useState } from 'react';
import { ImageSourcePropType } from 'react-native';
import { ArrowRight } from 'phosphor-react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import Toast from 'react-native-toast-message';

// Component import
import {
  DecreasingContainer,
  Button,
  DefaultComponent,
  UserAvatar,
} from '@components/index';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { SignUpRoutes } from '..';

// Theme import
import theme from '@theme/index';

// Hook import
import { useSignUpForm } from '@hooks/useSignUpForm';

// Style import
import { Container, AvatarWrapper } from './styles';

export const Step4: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<SignUpRoutes, 'Step4'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // Hook
  const { setUser } = useSignUpForm();

  // State
  const [avatar, setAvatar] = useState<string | null>(null);

  const imageSource: ImageSourcePropType = avatar
    ? { uri: avatar }
    : require('../../../assets/default_avatar.png');

  function handleContinue() {
    if (!avatar) {
      return Toast.show({
        type: 'error',
        text1: 'Adicione uma imagem',
        text2: 'Sua identidade visual é importante.',
      });
    }

    setUser(prevUserData => ({ ...prevUserData, urlImagem: avatar }));

    return navigation.navigate('Step5');
  }

  return (
    <Container>
      <DefaultComponent
        headerProps={{ goBack: () => navigation.goBack() }}
        highlightProps={{
          subtitle: 'Passo 4 de 5',
          title: 'Logo da empresa',
        }}
        key="default-component-step4"
      />

      <DecreasingContainer>
        <AvatarWrapper>
          <UserAvatar
            imageSource={imageSource}
            handleSetAvatar={setAvatar}
            size="XXL"
          />
        </AvatarWrapper>
      </DecreasingContainer>

      <Button
        label="Continuar"
        size="XL"
        icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={handleContinue}
      />
    </Container>
  );
};
