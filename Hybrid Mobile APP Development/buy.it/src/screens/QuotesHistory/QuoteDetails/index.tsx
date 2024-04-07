import { Fragment, useLayoutEffect, useState } from 'react';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import Toast from 'react-native-toast-message';
import { CompositeScreenProps } from '@react-navigation/native';
import { MainNavigationRoutes } from '@routes/index';
import { Clock } from 'phosphor-react-native';
import Icon from '@expo/vector-icons/MaterialCommunityIcons';

// Type import
import { QuotesHistoryRoutes } from '..';
import { Quote, QuoteQuery } from '@dtos/quote';

// Component import
import {
  DecreasingContainer,
  WrapperPage,
  Header,
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
  ValueBigger,
  Actions
} from './styles';
import { Flex, ScrollableContent } from '@global/styles';

// Services import
import { api } from '@services/api';

// Utils import
import theme from '@theme/index';
import { toMaskedCurrency } from '@utils/masks';
import { CustomModal } from '@components/Modal';
import NewStatus from './NewStatus/NewStatus';

export const QuoteDetails: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<QuotesHistoryRoutes, 'QuoteDetails'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ route, navigation }) => {
  const [quote, setQuote] = useState<Quote>({} as Quote);
  const [isLoading, setIsLoading] = useState(false);
  const [showDetails, setShowDetails] = useState(false);
  const [isModalVisible, setModalVisible] = useState(false);
  const [modalTitle, setModalTitle] = useState("");
  const [modalSubtitle, setModalSubtitle] = useState("");

  const toggleModal = () => setModalVisible(previousState => !previousState);

  const { id } = route.params;

  const fetchData = async () => {
    try {
      const { data } = await api.get(`/cotacoes/${id}`);
      setQuote(data);
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível carregar a cotação',
      });
    } finally {
      setIsLoading(false);
    }
  };

  const handleUpdateQuote = async (body: QuoteQuery, id: number, goBack?: boolean) => {
    try {
      const { data } = await api.put(`/cotacoes/${id}`, body);
      
      if (data.id) {
        Toast.show({
          type: 'success',
          text1: 'Cotação atualizada com sucesso',
        });
        
        if (goBack) navigation.navigate("History");
      }
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível atualizar a cotação',
      });
    }
  };

  useLayoutEffect(() => {
    fetchData();
    console.log(quote);
  }, []);

  const total = quote.valorProduto * quote.quantidadeProduto;

  type PriorityLabel = {
    [key: number]: string;
  }

  const priorityLabel: PriorityLabel = {
    1: 'importância baixa',
    2: 'importância média',
    3: 'importância alta'
  }

  const hasDetails = quote.produto && 
  (quote.produto.marca || quote.produto.cor || quote.produto.material || quote.produto.tamanho);

  const handleCancel = () => {
    setModalTitle("Cancelar");
    setModalSubtitle("Tem certeza que deseja cancelar a cotação? Pode ser que em alguns dias um fornecedor te atenda.");
    toggleModal();
  }

  const handleConfirm = () => {
    setModalTitle("Concluir");
    setModalSubtitle("A venda já foi finalizada com o vendedor?");
    toggleModal();
  }

  return (
    <WrapperPage>
      <ScrollableContent style={{ paddingTop: 10 }}>
        {isLoading || !quote.id ?
            <TextIndicator>Carregando cotação...</TextIndicator> : 
            (
              <Fragment>
                <DefaultComponent
                  highlightProps={{
                    title: quote.produto.nome,
                    subtitle: "Detalhes da cotação de ",
                  }}
                  headerProps={{ goBack: () => navigation.goBack() }}
                  key="default-component-quote-details"
                />
                <TimeAgo>
                  <Clock size={theme.FONT_SIZE.SM} color={theme.COLORS.GRAY_300} />
                  <Subtitle>Há 2 dias</Subtitle>
                </TimeAgo>

                <DecreasingContainer>
                  <Container>
                    <Label>Status</Label>
                    <Value>{quote.status.nome}</Value>
                  </Container>

                  <Container>
                    <Label>Quantidade</Label>
                    <Value>{quote.quantidadeProduto} unidades</Value>
                  </Container>

                  <Container>
                    <Label>Preço</Label>
                    <Flex>
                      <Flex>
                        <Value>Unidade: </Value>
                        <Price>{toMaskedCurrency(quote.valorProduto, true)}</Price>
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
                      <Icon name={quote.produto.departamento.icone || "paperclip"} size={theme.FONT_SIZE.SM} color={theme.COLORS.PRIMARY} />
                      <Value>{quote.produto.departamento.nome}</Value>
                    </TextIcon>
                  </Container>

                  {quote.produto.tags.length > 0 && (
                    <Container>
                      <Label>Tags</Label>
                      <Tags>
                        {quote.produto.tags.map(tag => <Chip value={tag.nome} />)}
                      </Tags>
                    </Container>
                  )}

                  <Container>
                    <Label>Prioridade</Label>

                    <Value>
                      <ValueBigger>Preço baixo</ValueBigger>{'\n'}
                      {quote.prioridadePreco}: {priorityLabel[quote.prioridadePreco]}
                    </Value>
                    <Value>
                      <ValueBigger>Qualidade</ValueBigger>{'\n'}
                      {quote.prioridadeQualidade}: {priorityLabel[quote.prioridadeQualidade]}
                    </Value>
                    <Value>
                      <ValueBigger>Entrega</ValueBigger>{'\n'}
                      {quote.prioridadeEntrega}: {priorityLabel[quote.prioridadeEntrega]}
                    </Value>
                  </Container>

                  {showDetails && (
                    <Fragment>
                      {quote.produto.marca && (
                        <Container>
                          <Label>Marca</Label>
                          <Value>{quote.produto.marca}</Value>
                        </Container>
                      )}
                      {quote.produto.cor && (
                        <Container>
                          <Label>Cor</Label>
                          <Value>{quote.produto.cor}</Value>
                        </Container>
                      )}
                      {quote.produto.tamanho && (
                        <Container>
                          <Label>Tamanho</Label>
                          <Value>{quote.produto.tamanho}</Value>
                        </Container>
                      )}
                      {quote.produto.material && (
                        <Container>
                          <Label>Material</Label>
                          <Value>{quote.produto.material}</Value>
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
                    <Button 
                      label="Cancelar"
                      size="SM"
                      backgroundColor={theme.COLORS.RED_DARK}
                      onPress={() => handleCancel()}
                    />

                    <Button 
                      label="Concluir"
                      size="SM"
                      backgroundColor={theme.COLORS.GREEN_800}
                      onPress={() => handleConfirm()}
                    />
                  </Actions>
                </DecreasingContainer>
              </Fragment>
            )
        }
      </ScrollableContent>

      <CustomModal
        modalProps={{ isVisible: isModalVisible }}
        title={`${modalTitle} cotação`}
        subtitle={modalSubtitle}
        onClose={toggleModal}
      >
        <NewStatus 
          modalTitle={modalTitle} 
          quote={quote}
          handleUpdateQuote={handleUpdateQuote}
        />
      </CustomModal>
    </WrapperPage>
  );
}
