import theme from '@theme/index';
import { RequirementContainer, RequirementText } from './styles';
import { Check, X } from 'phosphor-react-native';
import { ViewProps } from 'react-native';

interface Props extends ViewProps {
  fulfilled?: boolean;
  label: string;
}

export const Requirement: React.FC<Props> = ({
  fulfilled = false,
  label,
  ...rest
}) => {
  return (
    <RequirementContainer {...rest}>
      {fulfilled ? (
        <Check
          color={theme.COLORS.GREEN_700}
          weight="bold"
          size={theme.FONT_SIZE.MD}
        />
      ) : (
        <X color={theme.COLORS.RED} weight="bold" size={theme.FONT_SIZE.MD} />
      )}

      <RequirementText fulfilled={fulfilled}>{label}</RequirementText>
    </RequirementContainer>
  );
};
