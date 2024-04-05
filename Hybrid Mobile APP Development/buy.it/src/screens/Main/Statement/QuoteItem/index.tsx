import React from 'react';

import { Chip } from '@components/Chip';

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

export const QuoteItem: React.FC = () => {
  return (
    <Container>
      <Section>
        <QuoteIconContainer></QuoteIconContainer>
      </Section>

      <Section start>
        <QuoteName>Teste</QuoteName>
        <QuoteTag value="papelaria" />
        <QuoteQuantity>100 unidades</QuoteQuantity>
      </Section>

      <Section end>
        <QuoteDate>12/10/2023</QuoteDate>
        <QuotePrice>
          <QuotePriceSymbol>R$ </QuotePriceSymbol>
          0,50
        </QuotePrice>
      </Section>
    </Container>
  );
};
