import { toMaskedCurrency } from '@utils/masks';
import Icon from '@expo/vector-icons/MaterialCommunityIcons';

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
import theme from '@theme/index';

interface Item {
  quote: Quote;
  onPress: () => void;
}

export function QuoteItem ({ quote, onPress }: Item) {
  return (
    <Container onPress={onPress}>
      <Section>
        <QuoteIconContainer>
          <Icon 
            name={quote.produto.departamento.icone || "paperclip"} 
            size={theme.FONT_SIZE.XXL} color={theme.COLORS.PRIMARY} 
          />
        </QuoteIconContainer>
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
          {toMaskedCurrency(quote?.valorProduto.toFixed(2), false)}
        </QuotePrice>
      </Section>
    </Container>
  );
};
