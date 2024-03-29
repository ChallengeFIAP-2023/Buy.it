import React from 'react';
import {
  createNativeStackNavigator,
  NativeStackNavigationOptions,
  NativeStackScreenProps,
} from '@react-navigation/native-stack';

// Hook import
import { QuoteDetailsProvider } from '../../hooks/useCreateQuote';

// Type import
import { MainNavigationRoutes } from '@routes/index';

// Pages import

// Interfaces
export type CreateQuoteRoutes = {
  QuoteProposalDetails: undefined;
};

export const QuoteDetails: React.FC<
  NativeStackScreenProps<MainNavigationRoutes, 'QuoteDetails'>
> = () => {
  const Stack = createNativeStackNavigator<CreateQuoteRoutes>();

  const screenOptions: NativeStackNavigationOptions = {
    headerShown: false,
    gestureEnabled: true,
    animation: 'slide_from_right',
  };

  return (
    <QuoteDetailsProvider>
      <Stack.Navigator
        initialRouteName="QuoteProposalDetails"
        screenOptions={screenOptions}
      >
        <Stack.Screen name="QuoteProposalDetails" component={{}} />
      </Stack.Navigator>
    </QuoteDetailsProvider>
  );
};
