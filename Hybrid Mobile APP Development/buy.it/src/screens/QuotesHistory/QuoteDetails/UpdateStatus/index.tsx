import { Button } from "@components/Button";
import { Quote, QuoteQuery } from "@dtos/quote";
import { Fragment } from "react";
import { STATUS_OPTIONS } from "@utils/statusOptions";

interface UpdateStatusProps {
  modalTitle: string;
  quote: Quote;
  handleUpdateQuote: (body: QuoteQuery, id: number, goBack?: boolean) => Promise<void>;
}

export default function UpdateStatus({ modalTitle, quote, handleUpdateQuote }: UpdateStatusProps) {

  const handleUpdateStatus = () => {
    const idStatus = modalTitle === "Concluir" 
    ? STATUS_OPTIONS.concluded : STATUS_OPTIONS.closed;

    const finalBodyData: QuoteQuery = {
      dataAbertura: quote.dataAbertura,
      idComprador: Number(quote.comprador.id),
      idProduto: quote.produto.id,
      quantidadeProduto: quote.quantidadeProduto,
      valorProduto: quote.valorProduto,
      idStatus,
      prioridadeEntrega: quote.prioridadeEntrega,
      prioridadeQualidade: quote.prioridadeQualidade,
      prioridadePreco: quote.prioridadePreco,
      prazo: quote.prazo,
      dataFechamento: quote.dataFechamento,
    }

    handleUpdateQuote(finalBodyData, quote.id, true);

    
  };

  return(
    <Fragment>
      <Button 
        label={modalTitle} 
        size="MD" 
        onPress={handleUpdateStatus}
      />
    </Fragment>
  )
}