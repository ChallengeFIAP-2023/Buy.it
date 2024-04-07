import { Fragment, useEffect, useLayoutEffect, useState } from 'react';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import Toast from 'react-native-toast-message';

// Type import
import { Quote } from '@dtos/quote';

// Component import
import {
  DecreasingContainer,
  WrapperPage,
  Highlight,
  Button,
} from '@components/index';
import { Tabs } from './Tabs';

import { StatusFilterType, tabs } from './Tabs/constants';

// Style import
import { TextIndicator, QuotesWrapper, Container } from './styles';
import { QuoteItem } from './QuoteItem';
import { ScrollableContent } from '@global/styles';

// Services import
import { api } from '@services/api';

// Utils import
import { STATUS_OPTIONS } from '@utils/statusOptions';

// Hook import
import { useAuth } from '@hooks/useAuth';
import { QuotesHistoryRoutes } from '..';
import { CompositeScreenProps } from '@react-navigation/native';
import { MainNavigationRoutes } from '@routes/index';

export const QuotesList: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<QuotesHistoryRoutes, 'QuotesHistory'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  const [activeOption, setActiveOption] = useState<StatusFilterType>();
  const [quotes, setQuotes] = useState<Quote[]>([]);
  const [filteredQuotes, setFilteredQuotes] = useState<Quote[]>([]);
  const [isLoading, setIsLoading] = useState(false);

  const { user } = useAuth();

  const filterQuotes = (filterBy: number) => {
    setFilteredQuotes(quotes.filter(quote => quote.status.id === filterBy))
  }

  const fetchData = async () => {
    try {
      const { data } = await api.get(`/cotacoes/usuario/comprador/${user.id}`);
      setQuotes(data.content);
      setFilteredQuotes(data.content);
      setActiveOption(tabs[0]);
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível carregar as cotações',
      });
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  useEffect(() => {
    if(!activeOption) return;
    filterQuotes(STATUS_OPTIONS[activeOption.key]);
  }, [activeOption]);

  return (
    <WrapperPage>
      <ScrollableContent>
        <Highlight 
          title="Aqui você encontra suas cotações"  
          highlightedText="cotações" 
        />
        <DecreasingContainer>
          {isLoading ? 
            <TextIndicator>Carregando cotações...</TextIndicator> : 
            (
              <Fragment>
                <Tabs activeOption={activeOption} handleSelectTab={setActiveOption} />
                <QuotesWrapper>
                  {filteredQuotes && filteredQuotes.length > 0 ? (
                    filteredQuotes.map(item => (
                      <QuoteItem
                        onPress={() => navigation.navigate("QuoteDetails", { id: item.id })}
                        key={item.id} 
                        quote={item}
                      />
                    ))
                  ) : (
                    <Container>
                      <TextIndicator>Nenhuma cotação encontrada.</TextIndicator>
                      <Button 
                        size="SM" 
                        label="Nova cotação" 
                        style={{ alignSelf: 'center' }}
                        onPress={() => navigation.navigate("CreateQuote")} 
                      />
                    </Container>
                  )}
                {}
                </QuotesWrapper>
              </Fragment>
            )
          }
        </DecreasingContainer>
      </ScrollableContent>
    </WrapperPage>
  );
}
