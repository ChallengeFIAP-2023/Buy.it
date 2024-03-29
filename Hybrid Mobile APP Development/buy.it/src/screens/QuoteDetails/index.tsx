import React from 'react';
import {
  createNativeStackNavigator,
  NativeStackNavigationOptions,
  NativeStackScreenProps,
} from '@react-navigation/native-stack';

// Hook import
import { QuoteDetailsProvider } from '../../hooks/useQuoteDetails';
import { TagsProvider } from '../../hooks/useTags';

// Type import
import { MainNavigationRoutes } from '@routes/index';

// Pages import
import { Step1 } from '@screens/QuoteDetails/Step1';
import { Step2 } from '@screens/QuoteDetails/Step2';
import { Step3 } from '@screens/QuoteDetails/Step3';
import { Step4 } from '@screens/QuoteDetails/Step4';
import { Step5 } from '@screens/QuoteDetails/Step5';
import { DepartmentsProvider } from '@hooks/useDepartments';

// Interfaces
export type QuoteDetailsRoutes = {
  Step1: undefined;
  Step2: undefined;
  Step3: undefined;
  Step4: undefined;
  Step5: undefined;
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
    <TagsProvider>
      <DepartmentsProvider>
        <QuoteDetailsProvider>
          <Stack.Navigator initialRouteName="Step1" screenOptions={screenOptions}>
            <Stack.Screen name="Step1" component={Step1} />
            <Stack.Screen name="Step2" component={Step2} />
            <Stack.Screen name="Step3" component={Step3} />
            <Stack.Screen name="Step4" component={Step4} />
            <Stack.Screen name="Step5" component={Step5} />
          </Stack.Navigator>
        </QuoteDetailsProvider>
      </DepartmentsProvider>
    </TagsProvider>
  );
};
