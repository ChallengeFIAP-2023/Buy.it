// Style import
import { Container, Logo, BackButton, BackIcon, Empty } from './styles';

export interface Props {
  goBack?: () => void;
  showLogo?: boolean;
  noPadding?: boolean;
}

export function Header({ goBack, showLogo = true, noPadding = false }: Props) {
  return (
    <Container style={{
      paddingHorizontal: noPadding ? 0 : 30 
    }}>
      <BackButton onPress={goBack}>
        {typeof goBack === 'function' && <BackIcon />}
      </BackButton>

      {showLogo && <Logo />}

      <Empty />
    </Container>
  );
}
