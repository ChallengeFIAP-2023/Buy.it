// Style import
import { Container, Header } from './styles';

interface Props {
  children: React.ReactNode;
}

export function DecreasingContainer({ children }: Props) {
  return (
    <>
      <Header />
      <Container>
        {children}
      </Container>
    </>
  );
}
