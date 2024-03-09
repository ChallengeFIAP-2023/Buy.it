import { ViewProps, TouchableOpacity } from 'react-native';

// Style import
import { ChipContainer, ChipText } from './styles';

import { X } from 'phosphor-react-native';
import theme from '@theme/index';

interface Props extends ViewProps {
  value: string;
  removable?: boolean;
  onRemove?: () => void;
}

export function Chip({ value, removable = false, onRemove, ...rest }: Props) {
  return (
    <ChipContainer {...rest}>
      <ChipText>{value}</ChipText>

      {removable && (
        <TouchableOpacity onPress={onRemove}>
          <X color={'#fff'} weight="bold" size={theme.FONT_SIZE.SM} />
        </TouchableOpacity>
      )}
    </ChipContainer>
  );
}
