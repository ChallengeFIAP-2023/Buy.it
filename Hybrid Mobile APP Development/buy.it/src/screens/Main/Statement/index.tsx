import { Fragment, useLayoutEffect, useState } from 'react';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import Toast from 'react-native-toast-message';

// Type import
import { MainRoutes } from '..';
import { Quote } from '@dtos/quote';

// Component import
import {
  DecreasingContainer,
  WrapperPage,
  Highlight,
} from '@components/index';
import { Tabs } from './Tabs';

import { StatusFilterType, tabs } from './Tabs/constants';

// Style import
import { TextIndicator, QuotesWrapper } from './styles';
import { QuoteItem } from './QuoteItem';
import { ScrollableContent } from '@global/styles';

// Services import
import { api } from '@services/api';

// Utils import
import { STATUS_OPTIONS } from '@utils/statusOptions';

// Hook import
import { useAuth } from '@hooks/useAuth';

export function Statement({
  navigation,
}: NativeStackScreenProps<MainRoutes, 'Statement'>) {
  const [activeStep, setActiveStep] = useState<StatusFilterType>();
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
      setActiveStep(tabs[0]);
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

  useLayoutEffect(() => {
    fetchData();
  }, []);

  useLayoutEffect(() => {
    if(!activeStep) return;
    filterQuotes(STATUS_OPTIONS[activeStep.key]);
  }, [activeStep]);

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
                <Tabs activeStep={activeStep} handleSelectTab={setActiveStep} /><QuotesWrapper>
                  {filteredQuotes && filteredQuotes.length > 0 ? (
                    filteredQuotes.map(item => (
                      <QuoteItem 
                        key={item.id} 
                        quote={item}
                      />
                    ))
                  ) : (
                    <TextIndicator>Nenhuma cotação encontrada.</TextIndicator>
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
