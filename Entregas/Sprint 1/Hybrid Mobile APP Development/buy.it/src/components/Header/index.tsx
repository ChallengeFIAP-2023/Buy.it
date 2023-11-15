// Style import
import { Container, Logo, BackButton, BackIcon } from './styles';

export function Header() {
  return (
    <Container>
      <BackButton>
        <BackIcon />
      </BackButton>

      <Logo />
    </Container>
  );
}

