import Dropdown from 'react-native-input-select';

// Style import
import { DisplayContainer, Label, Value } from './styles';

import { CaretDown } from "phosphor-react-native";

interface Props {
    label: string;
    value: string;
}

export function Display({ label, value, ...rest }: Props) {
  return (
    <DisplayContainer>
      <Label>{label}</Label>
      <Value>{value}</Value>
    </DisplayContainer>
  );
}

