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
  quote: Quote
}

export function QuoteItem ({ quote }: Item) {
  return (
    <Container>
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
          {quote.valorProduto}
        </QuotePrice>
      </Section>
    </Container>
  );
};
