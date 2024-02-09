import React from 'react';
import {
  createNativeStackNavigator,
  NativeStackNavigationOptions,
  NativeStackScreenProps,
} from '@react-navigation/native-stack';

// Hook import
import { SignUpFormProvider } from '../../hooks/useSignUpForm';

// Type import
import { MainNavigationRoutes } from '@routes/index';

// Pages import
import { Step1 } from './Step1';
import { Step2 } from './Step2';
import { Step3 } from './Step3';
import { Step4 } from './Step4';
import { Step5 } from './Step5';

// Interfaces
export type SignUpRoutes = {
  Step1: undefined;
  Step2: undefined;
  Step3: undefined;
  Step4: undefined;
  Step5: undefined;
};

export const SignUp: React.FC<
  NativeStackScreenProps<MainNavigationRoutes, 'SignUp'>
> = () => {
  const Stack = createNativeStackNavigator<SignUpRoutes>();

  const screenOptions: NativeStackNavigationOptions = {
    headerShown: false,
    gestureEnabled: true,
    animation: 'slide_from_right',
  };

  return (
    <SignUpFormProvider>
      <Stack.Navigator initialRouteName="Step1" screenOptions={screenOptions}>
        <Stack.Screen name="Step1" component={Step1} />
        <Stack.Screen name="Step2" component={Step2} />
        <Stack.Screen name="Step3" component={Step3} />
        <Stack.Screen name="Step4" component={Step4} />
        <Stack.Screen name="Step5" component={Step5} />
      </Stack.Navigator>
    </SignUpFormProvider>
  );
};
