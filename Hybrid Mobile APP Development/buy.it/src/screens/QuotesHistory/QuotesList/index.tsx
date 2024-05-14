import { Fragment, useLayoutEffect, useState } from 'react';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';

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

// Utils import
import { STATUS_OPTIONS } from '@utils/statusOptions';

// Hook import
import { useAuth } from '@hooks/useAuth';
import { useQuote } from '@hooks/useQuote';

// Route import
import { QuotesHistoryRoutes } from '..';
import { MainNavigationRoutes } from '@routes/index';

export const QuotesList: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<QuotesHistoryRoutes, 'QuotesHistory'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  const [activeOption, setActiveOption] = useState<StatusFilterType>(tabs[0]);
  const [filteredQuotes, setFilteredQuotes] = useState<Quote[]>([]);

  const { user } = useAuth();
  const { fetchQuotesByBuyer, quotes, loading } = useQuote();

  const filterQuotes = () => {
    const filterBy = STATUS_OPTIONS[activeOption.key];
    setFilteredQuotes(quotes.filter(quote => quote.status.id === filterBy));
  };


  useLayoutEffect(() => {
    fetchQuotesByBuyer(user.id);
    filterQuotes();
  }, [quotes]);

  useLayoutEffect(() => {
    filterQuotes();
  }, [activeOption]);

  return (
    <WrapperPage>
      <ScrollableContent>
        <Highlight
          title="Aqui você encontra suas cotações"
          highlightedText="cotações"
        />
        <DecreasingContainer>
          {loading ? (
            <TextIndicator>Carregando cotações...</TextIndicator>
          ) : (
            <Fragment>
              <Tabs
                activeOption={activeOption}
                handleSelectTab={setActiveOption}
              />
              <QuotesWrapper>
                {filteredQuotes && filteredQuotes.length > 0 ? (
                  filteredQuotes.map(item => (
                    <QuoteItem
                      onPress={() =>
                        navigation.navigate('QuoteDetails', { id: item.id })
                      }
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
                      onPress={() => navigation.navigate('CreateQuote')}
                    />
                  </Container>
                )}
              </QuotesWrapper>
            </Fragment>
          )}
        </DecreasingContainer>
      </ScrollableContent>
    </WrapperPage>
  );
};
