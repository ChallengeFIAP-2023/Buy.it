// Style import
import { Container, LoadIndicator, LoadingText } from './styles';

export function Loading() {
  return (
    <Container>
      <LoadIndicator />
      <LoadingText>
        Aguarde enquanto preparamos o aplicativo para você 😉🔥
      </LoadingText>
    </Container>
  );
}
