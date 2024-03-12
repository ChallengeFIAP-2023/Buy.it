import React from 'react';
import { KeyboardAvoidingViewProps, Platform } from 'react-native';

// Style import
import { Container } from './styles';

const behavior = Platform.OS === 'ios' ? 'padding' : 'height';

interface Props extends KeyboardAvoidingViewProps {
  children: React.ReactNode;
}

export function WrapperPage({ children, ...rest }: Props) {
  return (
    <Container behavior={behavior} {...rest}>
      {children}
    </Container>
  );
}
