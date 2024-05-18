import { Quote } from "@dtos/quote";
import { Fragment, useState } from "react";
import { Label, StarButton, StarsContainer } from "./styles"
import { Flex } from "@global/styles";
import Icon from '@expo/vector-icons/MaterialCommunityIcons';
import theme from "@theme/index";
import { Input } from "@components/Input";
import { ReviewQuery } from "@dtos/review";
import { format } from "date-fns";
import { Button } from "@components/Button";
import { useQuote } from "@hooks/useQuote";

interface ReviewProps {
  quote: Quote;
  toggleReviewModal: () => void;
}

interface IStars {
  label: string;
  rating: number;
  setRating: React.Dispatch<React.SetStateAction<number>>;
}

interface IStar {
  rating: number;
  value: number;
  setRating: React.Dispatch<React.SetStateAction<number>>;
}

export default function Review({ quote, toggleReviewModal }: ReviewProps) {
  const [priceRating, setPriceRating] = useState(5);
  const [qualityRating, setQualityRating] = useState(5);
  const [deliveryRating, setDeliveryRating] = useState(5);
  const [description, setDescription] = useState("");

  const { handleNewReview } = useQuote();

  const handleRegisterReview = () => {
    const data = format(new Date(), 'dd-MM-yyyy');
    const finalBodyData: ReviewQuery = {
      idCotacao: quote.id,
      data,
      notaPreco: priceRating,
      notaQualidade: qualityRating,
      notaEntrega: deliveryRating,
      descricao: description
    }

    handleNewReview(finalBodyData);
    toggleReviewModal();
  };

  return(
    <Fragment>
      <Stars 
        label="Preço"
        rating={priceRating} 
        setRating={setPriceRating} 
      />
      <Stars 
        label="Qualidade"
        rating={qualityRating} 
        setRating={setQualityRating} 
      />
      <Stars 
        label="Entrega"
        rating={deliveryRating} 
        setRating={setDeliveryRating} 
      />
      <Input 
        label="Breve descrição" 
        numberOfLines={2}
        value={description}
        onChangeText={(value: string) => setDescription(value)}
        style={{
          marginBottom: 30
        }}
      />

      <Button 
        size='MD' 
        label='Enviar avaliação'
        onPress={() => handleRegisterReview()}
      />
    </Fragment>
  )
}

const Stars = ({ label, rating, setRating }: IStars) => {
  return(
    <StarsContainer>
      <Label>{label}</Label>
      <Flex style={{
        justifyContent: "flex-start"
      }}>
        <Star setRating={setRating} rating={rating} value={1} />
        <Star setRating={setRating} rating={rating} value={2} />
        <Star setRating={setRating} rating={rating} value={3} />
        <Star setRating={setRating} rating={rating} value={4} />
        <Star setRating={setRating} rating={rating} value={5} />
      </Flex>
    </StarsContainer>
  )
}

const Star = ({ rating, value, setRating }: IStar) => {
  const selected = value <= rating;
  const color = theme.COLORS[selected ? 'YELLOW' : 'GRAY_300'];
  const name = selected ? "star" : "star";
  return (
    <StarButton
      onPress={() => setRating(value)}
    >
      <Icon name={name} color={color} size={theme.FONT_SIZE.XXL}/>
    </StarButton>
  )
}