import { Fragment, useLayoutEffect, useState } from 'react';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import Toast from 'react-native-toast-message';
import { CompositeScreenProps } from '@react-navigation/native';
import { MainNavigationRoutes } from '@routes/index';
import { Clock } from 'phosphor-react-native';
import Icon from '@expo/vector-icons/MaterialCommunityIcons';

// Type import
import { QuotesHistoryRoutes } from '..';
import { Quote } from '@dtos/quote';

// Component import
import {
  DecreasingContainer,
  WrapperPage,
  Header,
  DefaultComponent,
  Chip,
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
  ValueBigger
} from './styles';
import { Flex, ScrollableContent } from '@global/styles';

// Services import
import { api } from '@services/api';

// Utils import
import { STATUS_OPTIONS } from '@utils/statusOptions';
import theme from '@theme/index';
import { toMaskedCurrency } from '@utils/masks';


export const QuoteDetails: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<QuotesHistoryRoutes, 'QuoteDetails'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ route, navigation }) => {
  const [quote, setQuote] = useState<Quote>({} as Quote);
  const [isLoading, setIsLoading] = useState(false);

  const { id } = route.params;

  const fetchData = async () => {
    try {
      const { data } = await api.get(`/cotacoes/${id}`);
      console.log(data);
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
                </DecreasingContainer>
              </Fragment>
            )
        }
      </ScrollableContent>
    </WrapperPage>
  );
}
