import React, { useEffect } from 'react';
import { Article, House, IconProps, User } from 'phosphor-react-native';
import {
  createBottomTabNavigator,
  BottomTabNavigationOptions,
  BottomTabScreenProps,
} from '@react-navigation/bottom-tabs';

// Theme import
import theme from '@theme/index';

// Hook import
import { useQuoteProposal } from '@hooks/useQuoteProposal';

// Component import
import { Text } from '@components/Tab';

// Type import
import { MainNavigationRoutes } from '@routes/index';

// Pages import
import { Home } from './Home';
import { Statement } from './Statement';
import { Profile } from './Profile';

// Interfaces
export type MainRoutes = {
  Home: undefined;
  Statement: undefined;
  Profile: undefined;
};

export const Main: React.FC<
  BottomTabScreenProps<MainNavigationRoutes, 'Main'>
> = ({ navigation, route }) => {
  const { proposal, setLastRouteNavigated } = useQuoteProposal();
  const Tab = createBottomTabNavigator<MainRoutes>();

  const { GRAY_100, GRAY_300 } = theme.COLORS;

  const screenOptions: BottomTabNavigationOptions = {
    headerShown: false,
    tabBarActiveBackgroundColor: theme.COLORS.GRAY_500,
    tabBarInactiveBackgroundColor: theme.COLORS.GRAY_500,
    tabBarStyle: {
      borderTopWidth: 0,
      height: 70,
    },
  };

  function iconProps(focused: boolean): IconProps {
    return {
      color: focused ? GRAY_100 : GRAY_300,
      size: theme.FONT_SIZE.XXL,
      weight: focused ? 'fill' : 'regular',
    };
  }

  // Verifico se há propostas pro usuário autenticado
  useEffect(() => {
    if (proposal) return navigation.navigate('QuoteProposal');
  }, [proposal]);

  // Verifica qual a última rota que o usuário estava, pra quando ele recusar ou aceitar a proposta continuar na mesma tela
  useEffect(() => {
    const getCurrentRoute = (routes: any) => {
      if (routes && routes.length > 0) {
        const state = routes[0].state;

        if (state?.history && state.history.length > 0) {
          const lastEntry = state.history[state.history.length - 1];
          const key = lastEntry.key;

          const lastRouteName = key.split('-')[0] as keyof MainRoutes;

          setLastRouteNavigated(lastRouteName);
        }
      }
    };

    getCurrentRoute(navigation.getState()?.routes);
  }, [navigation, route]);

  return (
    <Tab.Navigator initialRouteName="Home" screenOptions={screenOptions}>
      <Tab.Screen
        name="Home"
        component={Home}
        options={{
          tabBarIcon: ({ focused }) => <House {...iconProps(focused)} />,
          tabBarLabel: ({ focused }) => focused && <Text label="Início" />,
        }}
      />

      <Tab.Screen
        name="Statement"
        component={Statement}
        options={{
          tabBarIcon: ({ focused }) => <Article {...iconProps(focused)} />,
          tabBarLabel: ({ focused }) => focused && <Text label="Histórico" />,
        }}
      />

      <Tab.Screen
        name="Profile"
        component={Profile}
        options={{
          tabBarIcon: ({ focused }) => <User {...iconProps(focused)} />,
          tabBarLabel: ({ focused }) => focused && <Text label="Perfil" />,
        }}
      />
    </Tab.Navigator>
  );
};
