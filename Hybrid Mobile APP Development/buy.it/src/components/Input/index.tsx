import { Fragment } from 'react';
import { TextInputProps } from 'react-native';

// Theme import
import theme from '@theme/index';

// Style import
import { InputWrapper, Label, InputContainer, ErrorMessage } from './styles';

// Interface
interface Props extends TextInputProps {
  label?: string;
  error?: string;
}

export function Input({ label, error, ...rest }: Props) {
  const isInvalid = Boolean(error);
  const placeholderTextColor = isInvalid
    ? theme.COLORS.RED
    : theme.COLORS.GRAY_200;

  return (
    <InputWrapper>
      {Boolean(label) && <Label>{label}</Label>}

      <InputContainer
        {...rest}
        placeholderTextColor={placeholderTextColor}
        underlineColorAndroid="transparent"
        isInvalid={isInvalid}
      />

      {isInvalid && <ErrorMessage>{error}</ErrorMessage>}
    </InputWrapper>
  );
}
