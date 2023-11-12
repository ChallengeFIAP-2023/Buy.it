// Style import
import { Container } from './styles';

interface Props {
  children: React.ReactNode;
}

export function DecreasingContainer({ children }: Props) {
  return (
    <Container>
      {children}
    </Container>
  );
}
