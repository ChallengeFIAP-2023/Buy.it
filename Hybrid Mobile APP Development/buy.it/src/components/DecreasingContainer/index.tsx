import { ScrollViewProps } from 'react-native';

// Style import
import { Container, Header } from './styles';

interface Props extends ScrollViewProps {
  children: React.ReactNode;
}

export function DecreasingContainer({ children, ...rest }: Props) {
  return (
    <>
      <Header />
      <Container {...rest}>
        {children}
      </Container>
    </>
  );
}
