import { Fragment, useLayoutEffect, useState } from 'react';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { MainNavigationRoutes } from '@routes/index';
import { Clock } from 'phosphor-react-native';
import { format, parse } from 'date-fns';
import Icon from '@expo/vector-icons/MaterialCommunityIcons';

// Type import
import { QuotesHistoryRoutes } from '..';
import { QuoteQuery } from '@dtos/quote';

// Component import
import {
  DecreasingContainer,
  WrapperPage,
  DefaultComponent,
  Chip,
  Button,
} from '@components/index';

// Style import
import { 
  TextIndicator, 
  TimeAgo,
  Container,
  TextIcon,
  Label,
  Value,
  Price,
  Subtitle,
  Tags,
  BiggerValue,
  Actions
} from './styles';
import { Flex, ScrollableContent } from '@global/styles';

// Utils import
import theme from '@theme/index';
import { toMaskedCurrency } from '@utils/masks';
import { CustomModal } from '@components/Modal';

import UpdateStatus from './UpdateStatus';

// Hooks import
import { useQuote } from '@hooks/useQuote';
import { useAuth } from '@hooks/useAuth';
import { STATUS_OPTIONS } from '@utils/statusOptions';
import Review from './Review';

// Type
type PriorityLabel = {
  [key: number]: string;
}

export const QuoteDetails: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<QuotesHistoryRoutes, 'QuoteDetails'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ route, navigation }) => {

  const { 
    handleUpdateQuote,
    fetchQuotesByBuyer,
    fetchQuoteById, 
    retrievedQuote, 
    loading
  } = useQuote();
  const { user } = useAuth();

  const [showDetails, setShowDetails] = useState(false);
  const [isUpdateModalVisible, setUpdateModalVisible] = useState(false);
  const [isReviewModalVisible, setReviewModalVisible] = useState(false);
  const [modalTitle, setModalTitle] = useState("");
  const [modalSubtitle, setModalSubtitle] = useState("");

  const toggleUpdateModal = () => setUpdateModalVisible(modal => !modal);
  const toggleReviewModal = () => setReviewModalVisible(modal => !modal);

  const { id } = route.params;

  const updateQuote = async (body: QuoteQuery, id: number, goBack?: boolean) => {
    handleUpdateQuote(body, id);
    fetchQuotesByBuyer(user.id);
    if (goBack) navigation.navigate("QuotesHistory");
    toggleUpdateModal();
  };

  useLayoutEffect(() => {
    fetchQuoteById(id);
  }, []);

  const total = (retrievedQuote.valorProduto * retrievedQuote.quantidadeProduto).toFixed(2);

  const priorityLabel: PriorityLabel = {
    1: 'importância baixa',
    2: 'importância média',
    3: 'importância alta'
  }

  const hasDetails = retrievedQuote.produto && 
  (retrievedQuote.produto.marca || retrievedQuote.produto.cor || 
   retrievedQuote.produto.material || retrievedQuote.produto.tamanho);

  const handleCancel = () => {
    setModalTitle("Cancelar");
    setModalSubtitle("Tem certeza que deseja cancelar a cotação? Pode ser que em alguns dias algum fornecedor te atenda.");
    toggleUpdateModal();
  }

  const handleConfirm = () => {
    setModalTitle("Concluir");
    setModalSubtitle("A venda já foi finalizada com o vendedor?");
    toggleUpdateModal();
  }

  const today = new Date();

  const differenceInDays = (date: Date) => { 
    const diffInMilliseconds = Math.abs(date.getTime() - today.getTime());
    return Math.ceil(diffInMilliseconds / (1000 * 60 * 60 * 24));
  }

  const openingDate = parse(retrievedQuote.dataAbertura || format(today, 'dd-MM-yyyy'), 'dd-MM-yyyy', new Date());
  const daysAgo = differenceInDays(openingDate);
  const daysAgoLabel = daysAgo === 0 ? "Hoje" : `Há ${daysAgo} dias`;

  return (
    <WrapperPage>
      <ScrollableContent style={{ paddingTop: 10 }}>
        {loading || !retrievedQuote.id ?
            <TextIndicator>Carregando cotação...</TextIndicator> : 
            (
              <Fragment>
                <DefaultComponent
                  highlightProps={{
                    title: retrievedQuote.produto.nome,
                    subtitle: "Detalhes da cotação de ",
                  }}
                  headerProps={{ goBack: () => navigation.goBack() }}
                  key="default-component-quote-details"
                />
                <TimeAgo>
                  <Clock size={theme.FONT_SIZE.SM} color={theme.COLORS.GRAY_300} />
                  <Subtitle>{daysAgoLabel}</Subtitle>
                </TimeAgo>

                <DecreasingContainer>
                  <Container>
                    <Label>Status</Label>
                    <Value>{retrievedQuote.status.nome}</Value>
                  </Container>

                  <Container>
                    <Label>Quantidade</Label>
                    <Value>{retrievedQuote.quantidadeProduto} unidades</Value>
                  </Container>

                  <Container>
                    <Label>Preço</Label>
                    <Flex>
                      <Flex>
                        <Value>Unidade: </Value>
                        <Price>{toMaskedCurrency(retrievedQuote.valorProduto.toFixed(2), true)}</Price>
                      </Flex>
                      <Flex>
                        <Value>Total: </Value>
                        <Price>{toMaskedCurrency(total, true)}</Price>
                      </Flex>
                    </Flex>
                  </Container>

                  <Container>
                    <Label>Departamento</Label>
                    <TextIcon>
                      <Icon 
                        name={retrievedQuote.produto.departamento.icone} 
                        size={theme.FONT_SIZE.SM} 
                        color={theme.COLORS.PRIMARY} 
                      />
                      <Value>{retrievedQuote.produto.departamento.nome}</Value>
                    </TextIcon>
                  </Container>

                  {retrievedQuote.produto.tags.length > 0 && (
                    <Container>
                      <Label>Tags</Label>
                      <Tags>
                        {retrievedQuote.produto.tags.map(tag => <Chip key={tag.id} value={tag.nome} />)}
                      </Tags>
                    </Container>
                  )}

                  <Container>
                    <Label>Prioridade</Label>

                    <Value>
                      <BiggerValue>Preço baixo</BiggerValue>{'\n'}
                      {retrievedQuote.prioridadePreco}: {priorityLabel[retrievedQuote.prioridadePreco]}
                    </Value>
                    <Value>
                      <BiggerValue>Qualidade</BiggerValue>{'\n'}
                      {retrievedQuote.prioridadeQualidade}: {priorityLabel[retrievedQuote.prioridadeQualidade]}
                    </Value>
                    <Value>
                      <BiggerValue>Entrega</BiggerValue>{'\n'}
                      {retrievedQuote.prioridadeEntrega}: {priorityLabel[retrievedQuote.prioridadeEntrega]}
                    </Value>
                  </Container>

                  {showDetails && (
                    <Fragment>
                      {retrievedQuote.produto.marca && (
                        <Container>
                          <Label>Marca</Label>
                          <Value>{retrievedQuote.produto.marca}</Value>
                        </Container>
                      )}
                      {retrievedQuote.produto.cor && (
                        <Container>
                          <Label>Cor</Label>
                          <Value>{retrievedQuote.produto.cor}</Value>
                        </Container>
                      )}
                      {retrievedQuote.produto.tamanho && (
                        <Container>
                          <Label>Tamanho</Label>
                          <Value>{retrievedQuote.produto.tamanho}</Value>
                        </Container>
                      )}
                      {retrievedQuote.produto.material && (
                        <Container>
                          <Label>Material</Label>
                          <Value>{retrievedQuote.produto.material}</Value>
                        </Container>
                      )}
                    </Fragment>
                  )}

                  {hasDetails && (
                    <Button 
                      label={`${showDetails ? "Mais" : "Menos"} detalhes`}
                      type="secondary"
                      size="LG"
                      onPress={() => setShowDetails(!showDetails)}
                      iconFirst={!showDetails}
                      icon={
                        <Icon 
                          name={showDetails ? "chevron-down" : "chevron-up"}
                          size={theme.FONT_SIZE.XXL}
                          color={theme.COLORS.PRIMARY_LIGHTER}
                        />
                      }
                    />
                  )}

                  <Actions>
                    {retrievedQuote.status.id !== STATUS_OPTIONS.closed && 
                    retrievedQuote.status.id !== STATUS_OPTIONS.concluded && (
                      <Button 
                        label="Cancelar"
                        size="SM"
                        backgroundColor={theme.COLORS.RED_DARK}
                        onPress={() => handleCancel()}
                      />
                    )}
                    
                    {retrievedQuote.status.id !== STATUS_OPTIONS.concluded && (
                      <Button 
                        label="Concluir"
                        size="SM"
                        backgroundColor={theme.COLORS.GREEN_800}
                        onPress={() => handleConfirm()}
                      />
                    )}
                  </Actions>

                  {retrievedQuote.status.id === STATUS_OPTIONS.concluded && (
                    <Button 
                      size='MD' 
                      label='Avaliar fornecedor'
                      icon={
                        <Icon 
                          name="star-shooting" 
                          size={theme.FONT_SIZE.XL} 
                          color={theme.COLORS.WHITE} 
                        />
                      }
                      style={{
                        marginBottom: 40
                      }}
                      onPress={() => toggleReviewModal()}
                    />
                  )}
                </DecreasingContainer>
              </Fragment>
            )
        }

      </ScrollableContent>

      <CustomModal
        modalProps={{ isVisible: isUpdateModalVisible }}
        title={`${modalTitle} cotação`}
        subtitle={modalSubtitle}
        onClose={toggleUpdateModal}
      >
        <UpdateStatus 
          modalTitle={modalTitle} 
          quote={retrievedQuote}
          handleUpdateQuote={updateQuote}
        />
      </CustomModal>

      <CustomModal
        modalProps={{ isVisible: isReviewModalVisible }}
        title={`O que você achou do fornecedor?`}
        subtitle="Isso ajuda nosso sistema a escolher sempre os melhores fornecedores aos nossos usuários"
        onClose={toggleReviewModal}
      >
        <Review 
          quote={retrievedQuote}
          toggleReviewModal={toggleReviewModal} 
        />
      </CustomModal>
    </WrapperPage>
  );
}
