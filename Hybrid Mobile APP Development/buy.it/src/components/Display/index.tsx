import { Trash } from 'phosphor-react-native';
import { TouchableOpacity } from 'react-native';

// Theme import
import theme from '@theme/index';

// Style import
import { TextWrapper, DisplayContainer, Label, Value } from './styles';

interface Props {
  label: string;
  value: string;
  handleDelete?: () => void;
}

export function Display({ label, value, handleDelete }: Props) {
  return (
    <DisplayContainer>
      <TextWrapper>
        <Label>{label}</Label>
        <Value>{value}</Value>
      </TextWrapper>

      <TouchableOpacity onPress={handleDelete}>
        <Trash size={theme.FONT_SIZE.XL} color={theme.COLORS.RED} />
      </TouchableOpacity>
    </DisplayContainer>
  );
}
