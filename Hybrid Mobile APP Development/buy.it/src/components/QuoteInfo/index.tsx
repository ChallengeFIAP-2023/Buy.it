import { Proposal } from '@dtos/proposal';
import { Quote } from '@dtos/quote';
import { Fragment } from 'react';
import { Container, Label, Value } from './styles';
import { toMaskedCurrency } from '@utils/masks';

interface AttributeProps {
  label: string, 
  value: string | number | null, 
  contained?: boolean 
}

interface QuoteInfoProps {
  quote: Proposal | Quote, 
  contained?: boolean,
  showMainInformation?: boolean
}

export const QuoteInfo = ({ quote, contained = false, showMainInformation = false }: QuoteInfoProps) => {
  return(
    <Fragment>
      {showMainInformation && (
        <Fragment>
          <Attribute 
            label='Produto' 
            value={`${quote.produto.nome} (x${quote.quantidadeProduto})`} 
            contained={contained}
          />
          <Attribute 
            label='Prazo' 
            value={`${quote.prazo} dia(s)`} 
            contained={contained}
          />
          <Attribute 
            label='Preço' 
            value={toMaskedCurrency(String(quote.valorProduto.toFixed(2)), true)} 
            contained={contained}
          />
        </Fragment>
      )}
      <Attribute 
        label='Marca' 
        value={quote.produto.marca} 
        contained={contained}
      />
      <Attribute 
        label='Cor' 
        value={quote.produto.cor} 
        contained={contained}
      />
      <Attribute 
        label='Tamanho' 
        value={quote.produto.tamanho} 
        contained={contained}
      />
      <Attribute 
        label='Material' 
        value={quote.produto.material} 
        contained={contained}
      />
    </Fragment>
  )
}

const Attribute = ({ label, value, contained = false }: AttributeProps) => {

  if (!value) return;

  return (
    <Container contained={contained}>
      <Label contained={contained}>{label}</Label>
      <Value>{value}</Value>
    </Container>
  )
}