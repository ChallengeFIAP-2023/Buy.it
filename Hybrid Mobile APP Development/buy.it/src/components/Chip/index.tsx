import { ViewProps, TouchableOpacity } from 'react-native';
import { X } from 'phosphor-react-native';

// Theme import
import theme from '@theme/index';

// Style import
import { ChipContainer, ChipText } from './styles';

interface Props extends ViewProps {
  value: string;
  onRemove?: () => void;
}

export function Chip({ value, onRemove, ...rest }: Props) {
  return (
    <ChipContainer {...rest}>
      <ChipText>{value}</ChipText>

      {onRemove && (
        <TouchableOpacity onPress={onRemove}>
          <X color={'#fff'} weight="bold" size={theme.FONT_SIZE.SM} />
        </TouchableOpacity>
      )}
    </ChipContainer>
  );
}
