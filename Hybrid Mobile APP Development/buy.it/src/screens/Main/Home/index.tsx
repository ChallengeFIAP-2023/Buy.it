import React, { Fragment, useLayoutEffect, useState } from 'react';
import Toast from 'react-native-toast-message';
import { CompositeScreenProps } from '@react-navigation/native';

// Component import
import { 
  WrapperPage, 
  DecreasingContainer, 
  Button,
  UserAvatar
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
  UserName,
  UserCNPJ,
  UserInfo,
  AvatarWrapper
} from './styles';

// Hook import
import { useAuth } from '@hooks/useAuth';

// Service import
import { api } from '@services/api';

// Type import
import { Quote } from '@dtos/quote';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { MainRoutes } from '..';

// Theme import
import theme from '@theme/index';
import { MainNavigationRoutes } from '@routes/index';
import { ImageSourcePropType, TouchableOpacity } from 'react-native';
import { Bell } from 'phosphor-react-native';
import { toMaskedCNPJ } from '@utils/masks';

export function Home({
  navigation,
}: CompositeScreenProps<NativeStackScreenProps<MainRoutes, 'Home'>,
  NativeStackScreenProps<MainNavigationRoutes>>) {

  const [quotes, setQuotes] = useState<Quote[]>([]);

  const { user } = useAuth();

  const fetchData = async () => {
    try {
      const { data } = await api.get(`/cotacoes/usuario/comprador/${user.id}`);
      setQuotes(data.content);
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível carregar as cotações',
      });
    }
  };

  useLayoutEffect(() => {
    fetchData();
  }, []);

  const imageSource: ImageSourcePropType = user.urlImagem
    ? { uri: user.urlImagem }
    : require('../../../assets/default_avatar.png');

  return (
    <WrapperPage>
      <ScrollableContent>

        {user && user.cnpj && (
          <Header>
            <AvatarWrapper>
              <UserAvatar
                imageSource={imageSource}
                size="SM"
              />
            </AvatarWrapper>
            <UserInfo>
              <UserName numberOfLines={1}>{user.nome}</UserName>
              <UserCNPJ>{toMaskedCNPJ(user.cnpj)}</UserCNPJ>
            </UserInfo>

            <TouchableOpacity>
              <Bell 
                color={theme.COLORS.GRAY_200} 
                size={theme.FONT_SIZE.XXL}
                weight="fill"
              />
            </TouchableOpacity>
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
              <Fragment>
                <Indicator>Nenhuma cotação encontrada.</Indicator>
              </Fragment>
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
