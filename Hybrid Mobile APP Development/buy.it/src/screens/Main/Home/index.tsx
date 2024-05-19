import React, { Fragment, useLayoutEffect, useState } from 'react';
import { CompositeScreenProps } from '@react-navigation/native';

// Component import
import { 
  WrapperPage, 
  DecreasingContainer, 
  Button,
  UserInfo
} from '@components/index';
import { QuoteItem } from '@screens/QuotesHistory/QuotesList/QuoteItem';

// Style import
import { ScrollableContent } from '@global/styles';
import { 
  Actions, 
  Indicator, 
  QuotesWrapper, 
  Title,
  Header,
  NotificationButton
} from './styles';

// Hook import
import { useAuth } from '@hooks/useAuth';

// Type import
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { MainRoutes } from '..';

// Theme import
import theme from '@theme/index';
import { MainNavigationRoutes } from '@routes/index';
import { ImageSourcePropType, TouchableOpacity } from 'react-native';
import { Bell } from 'phosphor-react-native';
import { useQuote } from '@hooks/useQuote';

export function Home({
  navigation,
}: CompositeScreenProps<NativeStackScreenProps<MainRoutes, 'Home'>,
  NativeStackScreenProps<MainNavigationRoutes>>) {

  const { user } = useAuth();
  const { quotes, fetchQuotesByBuyer } = useQuote();

  useLayoutEffect(() => {
    // if(!quotes || quotes.length === 0) fetchQuotesByBuyer(user.id);
  }, [quotes]);

  return (
    <WrapperPage>
      <ScrollableContent>

        {user && user.cnpj && (
          <Header>
            <UserInfo user={user}/>
            <NotificationButton>
              <Bell 
                color={theme.COLORS.GRAY_200} 
                size={theme.FONT_SIZE.XXL}
                weight="fill"
              />
            </NotificationButton>
          </Header>
        )}
        
        <DecreasingContainer scrollable>
          <Fragment>
            <Title>Suas cotações</Title>
            
            <QuotesWrapper>
            {quotes && quotes.length > 0 ? (
              quotes.slice(0, 2).map(item => (
                <QuoteItem
                  onPress={() => navigation.navigate("History")}
                  key={item.id} 
                  quote={item}
                  showTags={false}
                  contained={false}
                />
              ))
            ) : (
              <Indicator>Nenhuma cotação encontrada.</Indicator>
            )}
            <Actions>
              {quotes && quotes.length > 0 && (
                <Button 
                  size='MD' 
                  label='Gerenciar' 
                  style={{ flex: 1 }} 
                  backgroundColor={theme.COLORS.GRAY_400}
                  onPress={() => navigation.navigate("History")}
                />
              )}
              <Button 
                size='MD' 
                label='Nova cotação' 
                style={{ flex: 1 }} 
                onPress={() => navigation.navigate("CreateQuote")}
              />
            </Actions>
            </QuotesWrapper>
          </Fragment>
        </DecreasingContainer>
      </ScrollableContent>
    </WrapperPage>
  );
}
