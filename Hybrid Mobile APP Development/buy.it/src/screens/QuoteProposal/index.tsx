import React from 'react';
import {
  createNativeStackNavigator,
  NativeStackNavigationOptions,
  NativeStackScreenProps,
} from '@react-navigation/native-stack';

// Type import
import { MainNavigationRoutes } from '@routes/index';

// Pages import
import { QuoteProposalDetails } from './QuoteProposalDetails';

// Interfaces
export type QuoteProposalRoutes = {
  QuoteProposalDetails: undefined;
};

export const QuoteProposal: React.FC<
  NativeStackScreenProps<MainNavigationRoutes, 'QuoteProposal'>
> = () => {
  const Stack = createNativeStackNavigator<QuoteProposalRoutes>();

  const screenOptions: NativeStackNavigationOptions = {
    headerShown: false,
    gestureEnabled: true,
    animation: 'slide_from_right',
  };

  return (
    <Stack.Navigator
      initialRouteName="QuoteProposalDetails"
      screenOptions={screenOptions}
    >
      <Stack.Screen
        name="QuoteProposalDetails"
        component={QuoteProposalDetails}
      />
    </Stack.Navigator>
  );
};
