import { ArrowRight } from 'phosphor-react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import Toast from 'react-native-toast-message';
import { useLayoutEffect, useState } from 'react';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { QuoteDetailsRoutes } from '..';
import { Tag } from '@dtos/index';
import { Department } from '@dtos/department';
import { TFlatList } from 'react-native-input-select/lib/typescript/types/index.types';

// Service import
import { api } from '@services/api';

// Theme import
import theme from '@theme/index';

// Component import
import {
  Button,
  CustomDropdown,
  DecreasingContainer,
  DefaultComponent,
  WrapperPage,
} from '@components/index';

// Style import
import { ScrollableContent, Fieldset } from '@global/styles/index';

export const Step1: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<QuoteDetailsRoutes, 'Step1'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {

  // State
  const [tags, setTags] = useState<Tag[]>([]);
  const [departments, setDepartments] = useState<Department[]>([]);
  const [selectedTags, setSelectedTags] = useState([]);
  const [selectedDepartment, setSelectedDepartment] = useState<number>();
  const [isLoading, setIsLoading] = useState(true);

  // TODO: extrair ccódigos de get repetidos
  // TODO: adicionar token de autorização nas requisições
  const fetchData = async () => {
    try {
      const { data } = await api.get('/tags');
      setTags(data.content);
    } catch (error) {
      console.log(error);
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível buscar as tags',
      });
    } finally {
      setIsLoading(false);
    }

    try {
      const { data } = await api.get('/departamentos');
      setTags(data.content);
    } catch (error) {
      console.log(error);
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível buscar os departamentos',
      });
    } finally {
      setIsLoading(false);
    }
  };

  useLayoutEffect(() => {
    fetchData();
  }, []);

  const hasDepartments = !isLoading && departments.length >= 1;
  const departmentsOptions: TFlatList = departments.map(dep => ({
    label: dep.nome,
    value: dep.id,
  }));

  const hasTag = !isLoading && tags.length >= 1;
  const tagsOptions: TFlatList = tags.map(tag => ({
    label: tag.nome,
    value: tag.id,
  }));

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            title: 'De que tipo de produto você precisa hoje?',
            subtitle: 'Passo 1 de 5',
          }}
          key="default-component-quote-details"
        />

        <DecreasingContainer scrollable>
          <Fieldset>
          {!isLoading && departments && (
            <Fieldset>
              <CustomDropdown
                label="Departamento"
                isSearchable={hasDepartments}
                placeholder="Selecione o departamento relacionado"
                options={departmentsOptions}
                selectedValue={selectedTags}
                onValueChange={(value: number) => setSelectedDepartment(value)}
                listControls={{ emptyListMessage: 'Nenhum departamento encontrado' }}
              />
            </Fieldset>
          )}
          </Fieldset>

          <Fieldset>
          {!isLoading && tags && (
            <Fieldset>
              <CustomDropdown
                label="Tags relacionadas"
                isSearchable={hasTag}
                isMultiple
                placeholder="Selecione as tags relacionadas"
                options={tagsOptions}
                selectedValue={selectedTags}
                onValueChange={(value: []) => setSelectedTags(value)}
                listControls={{ emptyListMessage: 'Nenhuma tag encontrada' }}
              />
            </Fieldset>
          )}
          </Fieldset>
        </DecreasingContainer>
      </ScrollableContent>

      <Button
        label="Continuar"
        size="XL"
        icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={() => navigation.navigate('Step2')}
      />
    </WrapperPage>
  );
};
