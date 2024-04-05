import React, { useMemo } from 'react';
import {
  NativeStackNavigationProp,
  createNativeStackNavigator,
} from '@react-navigation/native-stack';

// Hook import
import { AuthProvider, useAuth } from '@hooks/useAuth';
import { QuoteProposalProvider } from '@hooks/useQuoteProposal';

// Screen import
import { SignIn } from '@screens/SignIn';
import { SignUp } from '@screens/SignUp';
import { CreateQuote } from '@screens/CreateQuote';
import { QuoteProposal } from '@screens/QuoteProposal';
import { Main, MainRoutes } from '@screens/Main';
import { NavigatorScreenParams } from '@react-navigation/native';

export type MainNavigationRoutes = {
  SignIn: undefined;
  SignUp: undefined;
  CreateQuote: undefined;
  QuoteProposal: undefined;
  Main: NavigatorScreenParams<MainRoutes> | undefined;
};

export type AppNavigatorRoutesProps =
  NativeStackNavigationProp<MainNavigationRoutes>;

export default function Routes() {
  // Hook
  const { user } = useAuth();

  // Navigator instance
  const Stack = createNativeStackNavigator<MainNavigationRoutes>();

  // const logged = !!user?.cnpj;
  const logged = true;

  const initialMainRoute = useMemo<keyof MainNavigationRoutes>(() => {
    if (logged) return 'Main';

    return 'SignIn';
  }, [logged]);

  const MainNavigation = useMemo(() => {
    const Navigator: React.FC = () => (
      <Stack.Navigator
        initialRouteName={initialMainRoute}
        screenOptions={{
          headerShown: false,
          gestureEnabled: true,
          animation: 'slide_from_right',
        }}
      >
        <Stack.Screen name="SignIn" component={SignIn} />
        <Stack.Screen name="SignUp" component={SignUp} />
        <Stack.Screen name="CreateQuote" component={CreateQuote} />
        <Stack.Screen name="QuoteProposal" component={QuoteProposal} />
        <Stack.Screen name="Main" component={Main} />
      </Stack.Navigator>
    );

    return <Navigator />;
  }, [initialMainRoute]);

  return (
    <AuthProvider>
      <QuoteProposalProvider>{MainNavigation}</QuoteProposalProvider>
    </AuthProvider>
  );
}
