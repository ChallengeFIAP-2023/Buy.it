import React from 'react';
import {
  createNativeStackNavigator,
  NativeStackNavigationOptions,
  NativeStackScreenProps,
} from '@react-navigation/native-stack';

// Type import
import { MainNavigationRoutes } from '@routes/index';

// Pages import
import { QuotesList } from '@screens/QuotesHistory/QuotesList';
import { QuoteDetails } from '@screens/QuotesHistory/QuoteDetails';

// Interfaces
export type QuotesHistoryRoutes = {
  QuotesHistory: undefined;
  QuoteDetails: { id: number } ;
};

export const History: React.FC<
  NativeStackScreenProps<MainNavigationRoutes, 'History'>
> = () => {
  const Stack = createNativeStackNavigator<QuotesHistoryRoutes>();

  const screenOptions: NativeStackNavigationOptions = {
    headerShown: false,
    gestureEnabled: true,
    animation: 'slide_from_right',
  };

  return (
    <Stack.Navigator initialRouteName="QuotesHistory" screenOptions={screenOptions}>
      <Stack.Screen name="QuotesHistory" component={QuotesList} />
      <Stack.Screen name="QuoteDetails" component={QuoteDetails} />
    </Stack.Navigator>
  );
};
