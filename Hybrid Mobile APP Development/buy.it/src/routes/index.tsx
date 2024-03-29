import { useMemo } from 'react';
import {
  NativeStackNavigationProp,
  createNativeStackNavigator,
} from '@react-navigation/native-stack';

// Hook import
import { AuthProvider, useAuth } from '@hooks/useAuth';

// Screen import
import { SignIn } from '@screens/SignIn';
import { SignUp } from '@screens/SignUp';
import { Profile } from '@screens/Profile';
import { QuoteDetails } from '@screens/QuoteDetails';

export type MainNavigationRoutes = {
  SignIn: undefined;
  SignUp: undefined;
  Profile: undefined;
  QuoteDetails: undefined;
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
    if (logged) return 'QuoteDetails';

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
        <Stack.Screen name="Profile" component={Profile} />
        <Stack.Screen name="QuoteDetails" component={QuoteDetails} />
      </Stack.Navigator>
    );

    return <Navigator />;
  }, [initialMainRoute]);

  return <AuthProvider>{MainNavigation}</AuthProvider>;
}
