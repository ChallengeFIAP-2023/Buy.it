import { toMaskedCurrency } from '@utils/masks';
import {
  Container,
  QuoteDate,
  QuoteName,
  QuotePrice,
  QuotePriceSymbol,
  QuoteQuantity,
  QuoteTag,
  Section,
  QuoteIconContainer,
} from './styles';

// Type import
import { Quote } from '@dtos/quote';

interface Item {
  quote: Quote;
  onPress: () => void;
}

export function QuoteItem ({ quote, onPress }: Item) {
  return (
    <Container onPress={onPress}>
      <Section>
        <QuoteIconContainer></QuoteIconContainer>
      </Section>

      <Section start>
        <QuoteName>{quote.produto.nome}</QuoteName>
        {quote.produto.tags.map(tag => <QuoteTag value={tag.nome} />)}
        <QuoteQuantity>{quote.quantidadeProduto} unidades</QuoteQuantity>
      </Section>

      <Section end>
        <QuoteDate>{quote.dataAbertura}</QuoteDate>
        <QuotePrice>
          <QuotePriceSymbol>R$ </QuotePriceSymbol>
          {toMaskedCurrency(quote?.valorProduto, false)}
        </QuotePrice>
      </Section>
    </Container>
  );
};
