import React, { useEffect } from 'react';
import { Article, House, IconProps, User, Folders } from 'phosphor-react-native';
import {
  createBottomTabNavigator,
  BottomTabNavigationOptions,
  BottomTabScreenProps,
} from '@react-navigation/bottom-tabs';

// Theme import
import theme from '@theme/index';

// Hook import
import { useQuoteProposal } from '@hooks/useQuoteProposal';
import { useAuth } from '@hooks/useAuth';
import { QuoteProvider } from '@hooks/useQuote';

// Component import
import { Text } from '@components/Tab';

// Type import
import { MainNavigationRoutes } from '@routes/index';

// Pages import
import { Home } from './Home';
import { History, QuotesHistoryRoutes } from '@screens/QuotesHistory';
import { Profile } from './Profile';
import { QuoteProposal, QuoteProposalRoutes } from '@screens/QuoteProposal';
import { NavigatorScreenParams } from '@react-navigation/native';



// Interfaces
export type MainRoutes = {
  Home: undefined;
  QuoteProposal: NavigatorScreenParams<QuoteProposalRoutes> | undefined;
  History: NavigatorScreenParams<QuotesHistoryRoutes> | undefined;
  Profile: undefined;
};

export const Main: React.FC<
  BottomTabScreenProps<MainNavigationRoutes, 'Main'>
> = ({ navigation, route }) => {
  const { proposal, setLastRouteNavigated } = useQuoteProposal();
  const { user } = useAuth();

  const Tab = createBottomTabNavigator<MainRoutes>();

  const { GRAY_100, GRAY_300 } = theme.COLORS;

  const screenOptions: BottomTabNavigationOptions = {
    headerShown: false,
    tabBarActiveBackgroundColor: theme.COLORS.PRIMARY_LOW_OPACITY,
    tabBarLabelPosition: "beside-icon",
    tabBarStyle: {
      borderTopWidth: 0,
      height: 70,
      backgroundColor: theme.COLORS.GRAY_800,
    },
    tabBarItemStyle: { 
      marginVertical: 15,
      display: "flex",
      alignItems: "center",
      justifyContent: "center",
      borderRadius: 20,
      width: "auto",
      flexGrow: 1
    },
    tabBarLabelStyle: {
      fontSize: theme.FONT_SIZE.MD,
      fontFamily: theme.FONT_FAMILY.RALEWAY.SEMIBOLD,
      color: theme.COLORS.WHITE
    },
  };

  function iconProps(focused: boolean): IconProps {
    return {
      color: focused ? GRAY_100 : GRAY_300,
      size: theme.FONT_SIZE.XL,
      weight: 'fill',
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
    <QuoteProvider>
      <Tab.Navigator initialRouteName="Home" screenOptions={screenOptions}>
        <Tab.Screen
          name="Home"
          component={Home}
          options={{
            tabBarIcon: ({ focused }) => <House {...iconProps(focused)} />,
            tabBarLabel: ({ focused }) => focused && <Text label="Início" />,
          }}
        />

        {user.isFornecedor && (
          <Tab.Screen
            name="QuoteProposal"
            component={QuoteProposal}
            options={{
              tabBarIcon: ({ focused }) => <Folders {...iconProps(focused)} />,
              tabBarLabel: ({ focused }) => focused && <Text label="Props." />,
            }}
          />
        )}

        <Tab.Screen
          name="History"
          component={History}
          options={{
            tabBarIcon: ({ focused }) => <Article {...iconProps(focused)} />,
            tabBarLabel: ({ focused }) => focused && <Text label="Hist." />,
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
    </QuoteProvider>
  );
};
