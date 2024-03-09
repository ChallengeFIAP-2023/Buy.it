import React from 'react';
import {
  createNativeStackNavigator,
  NativeStackNavigationOptions,
  NativeStackScreenProps,
} from '@react-navigation/native-stack';

// Hook import
import { QuoteDetailsProvider } from '../../hooks/useQuoteDetails';

// Type import
import { MainNavigationRoutes } from '@routes/index';

// Pages import
import { Step1 } from '@screens/QuoteDetails/Step1';
import { Step2 } from '@screens/QuoteDetails/Step2';

// Interfaces
export type QuoteDetailsRoutes = {
  Step1: undefined;
  Step2: undefined;
};

export const QuoteDetails: React.FC<
  NativeStackScreenProps<MainNavigationRoutes, 'QuoteDetails'>
> = () => {
  const Stack = createNativeStackNavigator<QuoteDetailsRoutes>();

  const screenOptions: NativeStackNavigationOptions = {
    headerShown: false,
    gestureEnabled: true,
    animation: 'slide_from_right',
  };

  return (
    <QuoteDetailsProvider>
      <Stack.Navigator initialRouteName="Step1" screenOptions={screenOptions}>
        <Stack.Screen name="Step1" component={Step1} />
        <Stack.Screen name="Step2" component={Step2} />
      </Stack.Navigator>
    </QuoteDetailsProvider>
  );
};
