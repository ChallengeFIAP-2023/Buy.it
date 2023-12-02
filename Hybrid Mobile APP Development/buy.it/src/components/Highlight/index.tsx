// Style import
import { Container, Subtitle, Flex, Title, HighlightedText } from "./styles";

// Interfaces
export interface Props {
  title: string;
  subtitle?: string;
  highlightedText?: string;
}

export function Highlight({ title, subtitle, highlightedText }: Props) {
  return (
    <Container>
      <Subtitle>{subtitle}</Subtitle>

      <Flex>
        <Title>{title}</Title>
        <HighlightedText>{highlightedText}</HighlightedText>
      </Flex>
    </Container>
  );
}
