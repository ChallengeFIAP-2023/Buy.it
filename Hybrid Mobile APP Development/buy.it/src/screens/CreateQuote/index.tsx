import React from 'react';
import {
  createNativeStackNavigator,
  NativeStackNavigationOptions,
  NativeStackScreenProps,
} from '@react-navigation/native-stack';

// Hook import
import { QuoteProvider } from '../../hooks/useQuote'

// Type import
import { MainNavigationRoutes } from '@routes/index';

// Pages import
import { Step1 } from '@screens/CreateQuote/Step1';
import { Step2 } from '@screens/CreateQuote/Step2';
import { Step3 } from '@screens/CreateQuote/Step3';
import { Step4 } from '@screens/CreateQuote/Step4';

// Interfaces
export type CreateQuoteRoutes = {
  Step1: undefined;
  Step2: undefined;
  Step3: undefined;
  Step4: undefined;
};

export const CreateQuote: React.FC<
  NativeStackScreenProps<MainNavigationRoutes, 'CreateQuote'>
> = () => {
  const Stack = createNativeStackNavigator<CreateQuoteRoutes>();

  const screenOptions: NativeStackNavigationOptions = {
    headerShown: false,
    gestureEnabled: true,
    animation: 'slide_from_right',
  };

  return (
    <QuoteProvider>
      <Stack.Navigator initialRouteName="Step1" screenOptions={screenOptions}>
        <Stack.Screen name="Step1" component={Step1} />
        <Stack.Screen name="Step2" component={Step2} />
        <Stack.Screen name="Step3" component={Step3} />
        <Stack.Screen name="Step4" component={Step4} />
      </Stack.Navigator>
    </QuoteProvider>
  );
};
