import { ScrollViewProps } from 'react-native';

// Style import
import { Container, Header } from './styles';

interface Props extends ScrollViewProps {
  children: React.ReactNode;
  scrollable?: boolean;
}

export function DecreasingContainer({
  children,
  scrollable = false,
  ...rest
}: Props) {
  return (
    <>
      <Header />
      <Container scrollable={scrollable} {...rest}>
        {children}
      </Container>
    </>
  );
}
