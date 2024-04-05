// Style import
import { Container, Subtitle, Flex, Title, HighlightedText } from './styles';

// Interfaces
export interface Props {
  title: string;
  subtitle?: string;
  highlightedText?: string;
  noPadding?: boolean;
}

export function Highlight({
  title,
  subtitle,
  highlightedText,
  noPadding,
}: Props) {
  return (
    <Container style={{ paddingHorizontal: noPadding ? 0 : 30 }}>
      <Subtitle>{subtitle}</Subtitle>

      <Flex>
        <Title>
          {title.replace(highlightedText ?? '', '')}
          <HighlightedText>{highlightedText}</HighlightedText>
        </Title>
      </Flex>
    </Container>
  );
}
