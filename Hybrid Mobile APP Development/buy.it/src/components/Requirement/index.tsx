interface Props {
    fulfilled?: boolean;
    label: string;
}

import theme from '@theme/index';
import { RequirementContainer, RequirementText } from './styles';
import { Check, X } from 'phosphor-react-native';

export const Requirement: React.FC<Props> = ({
    fulfilled = false,
    label,
  }) => {
    return (
        <RequirementContainer>
            {fulfilled ? (
                <Check color={theme.COLORS.GREEN_700} weight="bold" size={theme.FONT_SIZE.MD} />
            ) : (
                <X color={theme.COLORS.RED} weight="bold" size={theme.FONT_SIZE.MD} />
            )}
            
            <RequirementText 
                fulfilled={fulfilled}>
                    {label}
            </RequirementText>
        </RequirementContainer>
    );
}