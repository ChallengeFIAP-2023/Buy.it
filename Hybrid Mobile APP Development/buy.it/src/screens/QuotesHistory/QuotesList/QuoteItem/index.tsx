import { toMaskedCurrency } from '@utils/masks';
import Icon from '@expo/vector-icons/MaterialCommunityIcons';

import {
  Container,
  QuoteName,
  QuotePrice,
  QuotePriceSymbol,
  QuoteQuantity,
  QuoteTag,
  Section,
  QuoteIconContainer,
  QuoteStatus,
} from './styles';

// Type import
import { Quote } from '@dtos/quote';
import { PressableProps } from 'react-native';

// Theme import
import theme from '@theme/index';

interface Item extends PressableProps {
  quote: Quote;
  showTags?: boolean;
  contained?: boolean;
}

export function QuoteItem ({ 
  quote, 
  showTags = true, 
  contained = true,
   ...rest 
  }: Item) {

  const statusColors: { [key: string]: string } = {
    "Em andamento": theme.COLORS.YELLOW,
    "Recusado": theme.COLORS.RED,
    "Fechado": theme.COLORS.RED,
    "Aprovado": theme.COLORS.GREEN_800,
    "Concluído": theme.COLORS.GREEN_800,
  }

  const currentStatusColor = statusColors[quote.status.nome] || theme.COLORS.YELLOW;

  return (
    <Container {...rest} contained={contained}>
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
        {showTags && (
          quote.produto.tags.map(tag => <QuoteTag value={tag.nome} />)
        )}
        <QuoteQuantity>{quote.quantidadeProduto} unidades</QuoteQuantity>
      </Section>

      <Section end>
        <QuoteStatus style={{ color: currentStatusColor }}>{quote.status.nome}</QuoteStatus>
        <QuotePrice>
          <QuotePriceSymbol>R$ </QuotePriceSymbol>
          {toMaskedCurrency(quote?.valorProduto.toFixed(2), false)}
        </QuotePrice>
      </Section>
    </Container>
  );
};
