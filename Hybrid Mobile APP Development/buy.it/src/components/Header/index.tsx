// Style import
import { Container, Logo, BackButton, BackIcon, Empty } from './styles';

export interface Props {
  goBack?: () => void;
}

export function Header({ goBack }: Props) {
  return (
    <Container>
      <BackButton onPress={goBack}>
        {typeof goBack === 'function' && (
          <BackIcon />
        )}
      </BackButton>

      <Logo />

      <Empty />
    </Container>
  );
}

