// Style import
import { Container, Logo, BackButton, BackIcon } from './styles';

import { useNavigation } from '@react-navigation/native';

export function Header() {

  const navigation = useNavigation();

  const handleGoBack = () => {
    navigation.goBack();
  };

  return (
    <Container>
      <BackButton onPress={handleGoBack}>
        <BackIcon />
      </BackButton>

      {/* <Logo /> */}
    </Container>
  );
}

