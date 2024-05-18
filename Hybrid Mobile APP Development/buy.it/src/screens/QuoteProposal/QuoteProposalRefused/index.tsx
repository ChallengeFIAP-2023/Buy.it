import { useState } from 'react';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { QuoteProposalRoutes } from '..';

//Hooks import
import { useQuoteProposal } from '@hooks/useQuoteProposal';

// Component import
import {
  WrapperPage,
  DefaultComponent,
  CustomCheckBox,
  Input,
  Button,
} from '@components/index';

// Style import
import { Flex, ScrollableContent } from '@global/styles/index';
import {
  CheckBoxContainer,
  Container,
  Subtitle,
} from './styles';
import { format } from 'date-fns';
import { LogQuery } from '@dtos/log';
import { useAuth } from '@hooks/useAuth';
import { STATUS_OPTIONS } from '@utils/statusOptions';

export const QuoteProposalRefused: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<QuoteProposalRoutes, 'QuoteProposalRefused'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ route, navigation }) => {
  // Hook
  const { handleNewLog, handleProcessProposal } = useQuoteProposal();
  const { user } = useAuth();

  const [reasonIsProduct, setReasonIsProduct] = useState(false);
  const [reasonIsQuantity, setReasonIsQuantity] = useState(false);
  const [reasonIsPrice, setReasonIsPrice] = useState(false);
  const [reasonIsDeadline, setReasonIsDeadline] = useState(false);
  const [description, setDescription] = useState<string>("");

  const { id } = route.params;

  const handleRegisterLog = () => {
    const data = format(new Date(), 'dd-MM-yyyy');
    const finalBodyData: LogQuery = {
      idCotacao: id,
      data,
      idFornecedor: Number(user.id),
      recusadoPorPrazo: reasonIsDeadline,
      recusadoPorPreco: reasonIsPrice,
      recusadoPorProduto: reasonIsProduct,
      recusadoPorQuantidade: reasonIsQuantity,
      descricao: description === '' ? null : description,
      idStatus: STATUS_OPTIONS.repproved,
      valorOfertado: null
    }

    handleNewLog(finalBodyData);
    handleProcessProposal("decline");
  };

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          highlightProps={{
            title: "Queremos entender sua escolha",
            highlightedText: "sua escolha",
            subtitle: "Suas respostas ajudam nossso algoritmo a acertar nas próximas!",
          }}
          headerProps={{
            goBack: () => navigation.navigate("QuoteProposalDetails"),
            showLogo: false,
          }}
          key="default-component-quote-proposal-refused"
        />

        <Container>
          <Subtitle>Selecione as razões que te fizeram recusar</Subtitle>
        </Container>

        <CheckBoxContainer>
          <CustomCheckBox
            title="Não possuo este produto"
            checked={reasonIsProduct}
            onPress={() => setReasonIsProduct(!reasonIsProduct)}
          />

          <CustomCheckBox
            title="Não possuo suprir a quantidade"
            checked={reasonIsQuantity}
            onPress={() => setReasonIsQuantity(!reasonIsQuantity)}
          />

          <CustomCheckBox
            title="Não vendo por esse preço"
            checked={reasonIsPrice}
            onPress={() => setReasonIsPrice(!reasonIsPrice)}
          />

          <CustomCheckBox
            title="Não consigo entregar nesse prazo"
            checked={reasonIsDeadline}
            onPress={() => setReasonIsDeadline(!reasonIsDeadline)}
          />
        </CheckBoxContainer>

        <Container style={{
          marginTop: 20,
          paddingBottom: 100
        }}>
          <Input 
            label="Se preferir, descreva melhor o motivo"
            numberOfLines={2}
            value={description}
            onChangeText={(value: string) => setDescription(value)}
            style={{
              marginBottom: 25
            }}
          />

          <Button 
            size='MD' 
            label='Enviar respostas'
            onPress={() => handleRegisterLog()}
          />
        </Container>
      </ScrollableContent>
    </WrapperPage>
  );
};
